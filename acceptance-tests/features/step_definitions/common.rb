require 'net/ldap'

When /^I GET the (.*) resource$/ do |resource_type|
  @response = RestClient.get(path_for(resource_type)) { |response, request, results| response }
end

Then /^I get a list of (.*)$/ do |resource_type|
  body = JSON.parse(@response)
  body.should be_a_kind_of Array
  instance_variable_set("@#{resource_type}", body)
end

Then /^the response status should be (\d+)/ do |code|
  @response.code.should == code.to_i
end

When /^I POST to the (.*?) resource$/ do |resource|
  RestClient.post(path_for(resource), @request_json, :content_type => :json) do |response, request, result|
    @response = response
  end
end

def db_client
  @db_client ||= Mysql2::Client.new(:host => 'localhost', :username => ENV['DB_USERNAME'], :database => "#{ENV['DB_NAME']}_test")
end

After '@LDAPCleanup' do
  ldap_cleanup
end

def ldap_cleanup
  base = 'ou=LocalNew,ou=DevTest,dc=slidev,dc=org'
  group_base  = "ou=groups,#{base}"

  ldap_config = {
    :host => '127.0.0.1',
    :port => 10389,
    :base => group_base, # sets the treebase for search
    :auth => {
      :method => :simple,
      :username => 'cn=Admin,dc=slidev,dc=org',
      :password => 'test1234'
    }
  }

  Net::LDAP.open(ldap_config) do |ldap|
    # Remove user from the groups
    ['application_developer', 'Sandbox Administrator'].each do |group_id|
      group_dn = "cn=#{group_id},#{group_base}"
      group = ldap.search(:filter => Net::LDAP::Filter.eq('cn', group_id)).first
      if group
        removed = group[:memberuid].delete(@app_provider_email)
        ldap.replace_attribute(group_dn, :memberuid, group[:memberuid]) if removed
      end
    end

    # Delete the user
    ldap.delete(:dn => "cn=#{@app_provider_email},ou=people,#{base}")
  end
end