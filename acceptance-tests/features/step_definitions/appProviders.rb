



Given /^I have a JSON representation of an appProvider$/ do
  @request_json = appProvider_resource.to_json
end

And /^the response contains an appProvider$/ do
  @appProvider = JSON.parse(@response)
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