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