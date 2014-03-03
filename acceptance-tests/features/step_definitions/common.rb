When /^I GET the (.*) resource$/ do |resource_type|
  @response = RestClient.get(path_for(resource_type)) { |response, request, results| response }
end

Then /^I get a list of (.*)$/ do |resource_type|
  body = JSON.parse(@response)
  body.should be_a_kind_of Array
  instance_variable_set("@#{resource_type}", body)
end

Then /^each realm has an "identifier" and a "name"$/ do
  @realms.each do |realm|
    realm['identifier'].should_not be_nil
    realm['name'].should_not be_nil
  end
end

Then /^the response status should be (\d+)/ do |code|
  @response.code.should == code.to_i
end

# operator registration
Given /^I have a JSON representation of an operator$/ do
  @operator_json = operator_resource.to_json
end

When /^I POST to the (.*?) resource$/ do |resource|
  RestClient.post(path_for(resource), @operator_json, :content_type => :json) { |response, request, result|
    @response = response
    if response.code == 201
      @operator = JSON.parse(@response)
    end
  }
end


Then /^the operator has an identifier$/ do
  @operator['operatorId'].should_not be_nil
end

# operator retrieval
When /^I GET that operator resource$/ do
  @response = RestClient.get(@response.headers[:location], :accept => :json)
end

# operator modification
Given /^I modify that resource$/ do
  @operator['operatorName'] = 'Illinois Cloud'
  @operator['enabled'] = false
end

When /^I PUT that operator resource$/ do
  url = path_for('operators', @operator['operatorId'])
  @response = RestClient.put(url, @operator.to_json, :content_type => :json)
end

Then /^the operator should be modified$/ do
  url = path_for('operators', @operator['operatorId'])
  @response = RestClient.get(url)
  modified = JSON.parse(@response)
  modified['enabled'].should be false
  modified['operatorName'].should == @operator['operatorName']
end

Given /^I have an invalid JSON representation of an operator$/ do
  bad_operator = JSON.parse(@operator_json)
  bad_operator['operatorName'] = ''
  @operator_json = bad_operator.to_json
end

def operator_resource
  {
    'operatorName' => 'Illini Cloud',
    'apiUri' => 'http://localhost:8080',
    'connectorUri' => 'http://localhost:8080/connector',
    'enabled' => true
  }
end