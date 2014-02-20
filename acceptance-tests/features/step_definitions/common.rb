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
