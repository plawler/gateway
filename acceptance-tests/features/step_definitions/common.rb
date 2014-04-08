Given /^I have a JSON representation of an (.*)$/ do |resource_type|
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

#methods
def db_client
  @db_client ||= Mysql2::Client.new(:host => 'localhost', :username => ENV['DB_USERNAME'], :database => "#{ENV['DB_NAME']}_test")
end

def appProvider_resource
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


