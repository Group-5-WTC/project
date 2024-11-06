#!/bin/bash
cd $1

echo "Removing old output files in dir $1"

remove_file_if_exists() {
    local file="$1"
    if [ -f "$file" ]; then
        echo "File $file exists. Deleting..."
        rm "$file"
        if [ $? -eq 0 ]; then
            echo "File $file has been successfully deleted."
        else
            echo "Failed to delete $file."
        fi
    else
        echo "File $file does not exist."
    fi
}

# List of files to delete
files=("local_sqlite_database.db" "stores.json" "output.csv" "users.csv" "submit.dump")

# Loop through each file and call the function
for file in "${files[@]}"; do
    remove_file_if_exists "$file"
done
