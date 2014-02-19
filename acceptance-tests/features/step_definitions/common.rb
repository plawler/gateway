# Examples to be deleted
Given /^I am using Cucumber with RSpec$/ do
  true.should be_true
end

When /^I exercise (.*)$/ do |component|
  example_url = ENV['EXAMPLE_URL']
  case component
  when /capybara/i
    visit example_url
  when /rest\-client/i
    RestClient.get(example_url).code.should == 200
  when /webmock/i
    stub_request(:get, example_url).to_return(:body => 'hello')
    RestClient.get(example_url).body.should == 'hello'
  end
end

Then(/^all is green$/) do
end

When /^I use the (.*) resource/ do |resource|
  @resource = RestClient::Resource.new(path_for(resource))
end

Then /^a GET on the resource should contain a list of (.*)$/ do |resource_type|
  body = JSON.parse @resource.get
  body.should be_a_kind_of Array
  instance_variable_set("@#{resource_type}", body)
end

Then /^each realm has an "identifier" and a "name"/ do
  @realms.each do |realm|
    realm['identifier'].should_not be_nil
    realm['name'].should_not be_nil
  end
end

Then /^a GET on the resource should not be allowed$/ do
  @resource.get do |response, request, result|
    response.code.should == 405
  end
end