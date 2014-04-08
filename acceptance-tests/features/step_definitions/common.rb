require 'net/ldap'

Given /^I have a JSON representation of a(?:n?) (.*)$/ do |resource_type|
  resource_type.gsub!(' ','_')
  @request_json = send("#{resource_type}_resource").to_json
end

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

def app_provider_resource
  {
    'applicationProviderName' => 'Math Cats LLC',
    'organizationName' => 'Learning Kitties Holdings Inc',
    'user' => {
      'email' => 'john.smith@inbloom.org',
      'firstName' => 'John',
      'lastName' => 'Smith'
    }
  }
end

def operator_resource
  {
      'operatorName' => 'Illini Cloud',
      'primaryContactName' => 'Chief Illiniwek',
      'primaryContactEmail' => 'chief@illinicloud.edu',
      'primaryContactPhone' => '5185551212',
      'apiUri' => 'http://localhost:8080',
      'connectorUri' => 'http://localhost:8080/connector',
      'enabled' => true
  }
end

def account_validation_resource
  {
      'password' => 'P@5Sw0rd'
  }
end

def account_validation_with_an_invalid_password_resource
  {
      'password' => 'password'
  }
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
