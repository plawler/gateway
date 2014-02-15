class Test < Thor
  APP_ROOT = File.expand_path(File.dirname(__FILE__) + "/../")

  desc 'features', 'Run all cucumber features'
  def features
    run_command cucumber_command
  end

  private

  def cucumber_command
    "cd #{APP_ROOT} && cucumber --color"
  end

  def run_command(command)
    puts `#{command}`
  end
end