Given /^I have a JSON representation of an appProvider$/ do
  @request_json = appProvider_resource.to_json
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