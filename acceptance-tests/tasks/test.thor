class Test < Thor
  APP_ROOT = File.expand_path(File.dirname(__FILE__) + "/../")

  desc 'features', 'Run all non-wip cucumber features'
  def features
    run_command cucumber_command
  end

  desc 'feature', 'Run a single cucumber feature or a folder of features (if passed a value with a trailing slash)'
  def feature(feature='')
    run_command feature_command(feature)
  end

  private

  def feature_command(feature)
    to_run = feature.dup
    unless to_run.empty?
      to_run << '.feature' unless to_run =~ %r{(\.feature|\/)$}
      to_run = "features/#{to_run}"
    end
    "cd #{APP_ROOT} && cucumber --color #{to_run}"
  end

  def cucumber_command
    "cd #{APP_ROOT} && cucumber --color --tags ~@wip"
  end

  def run_command(command)
    puts `#{command}`
  end
end