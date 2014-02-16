class Resource
  include Paths
  attr_reader :resource

  def initialize(name)
    path = "#{ENV['GATEWAY_URL']}/#{path_for(resource)}"
    @resource = RestClient::Resource.new(path, :accept => 'application/json')
  end

  def get
    resource.get
  end

end