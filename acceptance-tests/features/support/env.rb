require 'dotenv'
Dotenv.load

require 'capybara'
Capybara.default_driver = :selenium

require 'webmock/cucumber'
WebMock.allow_net_connect!

require 'rest-client'

class TestWorld
  include Capybara::DSL
end

World do
  TestWorld.new
end