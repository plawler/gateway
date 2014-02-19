module Paths
  def path_for(resource)
    path = case resource
      when /^realms$/
        'realms'
      else
        resource
     end
    "#{ENV['GATEWAY_URL']}/#{path}"
  end
end