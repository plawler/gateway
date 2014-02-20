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

When /^I GET the (.*) resource$/ do |resource_type|
  @response = RestClient.get(path_for(resource_type)) { |response, request, results| response }
end

Then /^I get a list of (.*)$/ do |resource_type|
  body = JSON.parse(@response)
  body.should be_a_kind_of Array
  instance_variable_set("@#{resource_type}", body)
end

Then /^each realm has an "identifier" and a "name"/ do
  @realms.each do |realm|
    realm['identifier'].should_not be_nil
    realm['name'].should_not be_nil
  end
end

Then /^the response status should be (\d+)/ do |code|
  @response.code.should == code.to_i
end
