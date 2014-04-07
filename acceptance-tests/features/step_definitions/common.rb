require 'net-ldap'

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
  email = @app_provider_email
  initialize_ldap
  remove_user_group(email, 'application_developer')
  remove_user_group(email, 'Sandbox Administrator')
  delete_user(email)
end

# please forgive me, amplify cut, paste, wrangle and run
def initialize_ldap
  base = "ou=LocalNew,ou=DevTest,dc=slidev,dc=org"
  @people_base = "ou=people,#{base}"
  @group_base  = "ou=groups,#{base}"
  @ldap_conf = {
      :host => '127.0.0.1',
      :port => 10389,
      :base => @people_base,
      :auth => {
          :method => :simple,
          :username => 'cn=Admin,dc=slidev,dc=org',
          :password => 'test1234'
      }
  }
end

def delete_user(email_address)
  dn = get_DN(email_address)
  Net::LDAP.open(@ldap_conf) do |ldap|
    ldap.delete(:dn => dn)
  end
end

def read_user(email_address)
  filter = Net::LDAP::Filter.eq("mail", email_address)
  search_map_user_fields(filter, 1)[0]
end

def remove_user_group(email_address, group_id)
  group_member_attr = :memberUid
  # note the user is stored in the group via uid only
  user_dn = email_address
  group_dn = get_group_DN(group_id)

  filter = Net::LDAP::Filter.eq( "cn", group_id)
  Net::LDAP.open(@ldap_conf) do |ldap|
    group_found = ldap.search(:base => @group_base, :filter => filter).to_a()[0]

    if group_found
      removed = group_found[group_member_attr].delete(user_dn)
      if removed
        if group_found[group_member_attr].empty?
          if !ldap.delete(:dn => group_dn)
            raise ldap_ex(ldap, "Could not remove user #{user_dn} for group #{group_id}")
          end
        else
          if !ldap.replace_attribute(group_dn, group_member_attr, group_found[group_member_attr])
            raise ldap_ex(ldap, "Could not remove user #{user_dn} for group #{group_id}")
          end
        end
      end
    end
  end
end

def get_DN(cn)
  return "cn=#{cn},#{@people_base}"
end

def get_group_DN(group_id)
  return "cn=#{group_id},#{@group_base}"
end





