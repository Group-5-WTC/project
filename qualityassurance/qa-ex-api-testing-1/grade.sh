# clobber student code

# check if PetstoreTests directory exists
checkdir="$1/PetstoreTests"

if [ ! -d "$checkdir" ]; then
    echo "ERROR: PetstoreTests directory does not exist in $1"
    exit 1
fi

cp ./PetstoreTests.csproj $1/PetstoreTests/PetstoreTests.csproj
cp ./clean-restore-test.sh $1/clean-restore-test.sh
cp ./docker-compose.yml $1/docker-compose.yml

# run grading

cd $1
docker-compose run grader
docker-compose down