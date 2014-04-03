require 'dotenv'
Dotenv.load

class Gateway < Thor
  APP_ROOT = File.expand_path(File.dirname(__FILE__) + "/../")

  desc 'start', 'Start the gateway using the test database'
  def start
    run_command start_command
  end

  private

  def start_command
    path = File.join(APP_ROOT,'..','gateway','gateway-boot')
    mvn_command = "mvn -DsaveEmailToFile=true -Dspring.datasource.url=jdbc:mysql://localhost:3306/#{ENV['DB_NAME']}_test spring-boot:run"
    "cd #{path} && #{mvn_command}"
  end

  def run_command(command)
    puts command
    exec command
  end
end