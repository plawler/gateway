# Add your Before, After and Around hooks here

Before do
  db_cleaner = DatabaseCleaner.new(ENV['DB_NAME'])
  db_cleaner.reset_database
end