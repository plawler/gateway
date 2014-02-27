module Paths
  def path_for(resource, id=nil)
    path = case resource
      when /^realms$/
        'realms'
      else
        resource
     end
     url = "#{ENV['GATEWAY_URL']}/#{path}/#{id}"
  end
end