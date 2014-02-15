# Examples to be deleted
Given /^I want to use Cucumber$/ do
  true.should be_true
end

Given /^I want to use (.*)$/ do |component|
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

When(/^I run this feature$/) do
  true.should be_true
end

Then(/^all is green$/) do
  true.should be_true
end