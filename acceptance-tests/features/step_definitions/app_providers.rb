Given(/^I have already registered as an app provider$/) do
<<<<<<< HEAD
  RestClient.post(path_for('applicationProviders'), @request_json, :content_type => :json) do |response, request, result|
    @response = response
  end
=======
  @response = RestClient.post(path_for('applicationProviders'), @request_json, :content_type => :json)
>>>>>>> master
end

Then /^the response contains a link to the new app provider$/ do
  @app_provider = JSON.parse(@response)
  puts @app_provider
  puts @response.headers
end

Then /^the response contains a representation of the app provider$/ do
  app_provider = JSON.parse(@response)
  app_provider['applicationProviderId'].should_not be_nil
  %w(applicationProviderName organizationName).each do |attr|
    app_provider[attr].should == appProvider_resource[attr]
  end
  app_provider['user']['userId'].should_not be_nil
  %w(email firstName lastName).each do |attr|
    app_provider['user'][attr].should_not == appProvider_resource[attr]
  end
end

Then /^the response contains a location header for the app provider$/ do
  app_provider = JSON.parse(@response)
  @response.headers[:location].should == path_for('applicationProviders',app_provider['applicationProviderId'])
end

Then(/^the app provider receives an email with a verification link$/) do
  user_id = JSON.parse(@response)['user']['userId']
  user_id.should_not be_nil

  email_to = appProvider_resource['user']['email']
  dir = File.expand_path File.dirname(__FILE__)
  mail_file = File.join(dir,'..','..','..','gateway','gateway-boot','temp', "#{email_to}.eml")
  File.exists?(mail_file).should be_true

  verify_email_verification_link(mail_file, user_id)
end

def verify_email_verification_link(email_file, user_id)
  results = db_client.query("SELECT token FROM verifications WHERE user_id=#{db_client.escape(user_id.to_s)}")
  token = results.first['token']
  token.should_not be_nil, "Verification token not found for user #{user_id}"

  # Verify that the file contains a link with the correct token
  link_regex = /<a href=".+\/email_validation\?token=#{Regexp.escape(token)}/
  File.open(email_file).read.should match(link_regex)
end

When(/^I POST to the applicationProviders resource without (.*)$/) do |field|
  resource = appProvider_resource
  case field
    when 'organizationName'
      resource['organizationName'] = nil
    when 'firstName'
      resource['user']['firstName'] = nil
    when 'lastName'
      resource['user']['lastName'] = nil
    when 'email'
      resource['user']['email'] = nil
    else
      fail("Unexpected field #{field}")
  end
  @request_json = resource.to_json
  RestClient.post(path_for('applicationProviders'), @request_json, :content_type => :json) do |response, request, result|
    @response = response
  end
end

<<<<<<< HEAD
Given(/^I have JSON representation of an account validation$/) do
  resource = account_validation_resource
  @request_json = resource.to_json
end

When /^I POST to the verifications resource with a valid token$/ do
  user_id = JSON.parse(@response)['user']['userId']
  results = db_client.query("SELECT token FROM verifications WHERE user_id=#{db_client.escape(user_id.to_s)}")
  token = results.first['token']
  resource = "verifications/#{token}"
  RestClient.post(path_for(resource), @request_json, :content_type => :json) do |response, request, result|
    @response = response
  end
end

Then /^the response contains a representation of the verification$/ do
  pending # express the regexp above with the code you wish you had
end

Then /^the response contains a location header for the verification$/ do
  pending # express the regexp above with the code you wish you had
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

def account_validation_resource
  {
      'password' => "PAssw0rd"
  }
end
=======
When /^I GET that applicationProviders resource$/ do
  @response = RestClient.get(@response.headers[:location], :accept => :json)
end


When /^I modify my app provider information$/ do
  @app_provider = JSON.parse(@response)

  @app_provider['organizationName'] = 'Learning Forever Inc'
  @app_provider['user']['firstName'] = 'Jane'
  @app_provider['user']['lastName'] = 'Doe'
  @app_provider['user']['email'] = 'jane.doe@inbloom.org'
end

When /^I POST the update to applicationProviders resource$/ do
  @url = path_for('applicationProviders', @app_provider['applicationProviderId'])
  RestClient.post(@url, @app_provider.to_json, :content_type => :json) do |response, request, result|
    @response = response
  end
end

And /^my account information should be modified$/ do
  @response = RestClient.get(@url)

  modified = JSON.parse(@response)
  modified.should eq(@app_provider)
end
>>>>>>> master
