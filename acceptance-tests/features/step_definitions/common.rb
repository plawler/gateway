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

When /^I GET the (.*)/ do |resource|
  @response = Resource.new(resource).get
end

Then /^the response contains a list of realms/ do
  @response.should be_a_kind_of Array
end