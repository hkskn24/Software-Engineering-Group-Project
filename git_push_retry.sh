#!/bin/bash

# Set the maximum number of retries
MAX_RETRIES=20

# Initialize retry counter
retry_count=0

# Loop until successful push or maximum retries reached
while true; do
    # Attempt to push to Git repository
    git push
    exit_code=$?

    # Check if push was successful
    if [ $exit_code -eq 0 ]; then
        echo "Push successful!"
        break
    else
        # Increment retry counter
        retry_count=$((retry_count + 1))

        # Check if maximum retries reached
        if [ $retry_count -ge $MAX_RETRIES ]; then
            echo "Max retries reached. Push failed."
            break
        else
            # Wait before retrying
            echo "Push failed. Retrying in 5 seconds..."
            sleep 5
        fi
    fi
done
