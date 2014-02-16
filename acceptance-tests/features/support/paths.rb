module Paths
  def path_for(resource)
    case resource
      when /^realms$/
        'realms'
      else
        resource
    end
  end
end