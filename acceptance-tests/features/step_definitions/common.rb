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





