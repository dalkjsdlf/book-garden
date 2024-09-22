#!/bin/bash

# 컨테이너 이름 설정
DB_CONTAINER_NAME="msqlbgd8"
APP_CONTAINER_NAME="book-garden"

# 실행 중인 컨테이너 중에 msqlbgd8이 있는지 확인
RUNNING_CONTAINER=$(docker ps --filter "name=${DB_CONTAINER_NAME}" -q)

if [ -n "$RUNNING_CONTAINER" ]; then
    echo "Container '${DB_CONTAINER_NAME}' is ruaptnning. Stopping and removing it."

    echo "Container '${DB_CONTAINER_NAME}' is started to stop"
    # 컨테이너 중지
    docker stop ${DB_CONTAINER_NAME}
    echo "Container '${DB_CONTAINER_NAME}' has been stopped."

    echo "Container '${DB_CONTAINER_NAME}' is started to remove"
    # 컨테이너 삭제
    docker rm ${DB_CONTAINER_NAME}
    echo "Container '${DB_CONTAINER_NAME}' has been removed."
    echo "Container '${DB_CONTAINER_NAME}' has been stopped and removed."
fi

APP_IMAGE_NAME="book-garden-application"
RUNNING_IMAGE=$(docker images --filter "reference=${APP_IMAGE_NAME}" -q)

RUNNING_CONTAINER=$(docker ps --filter "name=${CONTAINER_NAME}" -q)

if [ -n "$RUNNING_CONTAINER" ]; then
    echo "Container '${APP_CONTAINER_NAME}' is running. Stopping and removing it."

    echo "Container '${APP_CONTAINER_NAME}' is started to stop"
    # 컨테이너 중지
    docker stop ${APP_CONTAINER_NAME}
    echo "Container '${APP_CONTAINER_NAME}' has been stopped."

    echo "Container '${APP_CONTAINER_NAME}' is started to remove"
    # 컨테이너 삭제
    docker rm ${APP_CONTAINER_NAME}
    echo "Container '${APP_CONTAINER_NAME}' has been removed."
    echo "Container '${APP_CONTAINER_NAME}' has been stopped and removed."
    docker image rm book-garden-application
fi

if [ -n "$RUNNING_IMAGE" ]; then
    echo "Image '${APP_IMAGE_NAME}' is running. Removing it."

    echo "Image '${APP_IMAGE_NAME}' is started to remove"
    # 이미지 삭제
    docker image rm ${APP_IMAGE_NAME}
    echo "Image '${APP_IMAGE_NAME}' has been removed."
    echo "Image '${APP_IMAGE_NAME}' has been removed."
fi


# 2. Docker Volume 체크 함수
check_and_create_volume() {
    local volume_name=$1

    # 볼륨이 있는지 확인
    if docker volume inspect "$volume_name" > /dev/null 2>&1; then
        echo "Volume '${volume_name}' already exists."
    else
        # 볼륨이 없으면 생성
        echo "Creating volume '${volume_name}'."
        docker volume create "$volume_name"
    fi
}

# 3. 두 개의 볼륨 체크 및 생성
VOLUME_DATA="msqlbgd-data"
VOLUME_CONF="msqlbgd-conf"

check_and_create_volume "$VOLUME_DATA"
check_and_create_volume "$VOLUME_CONF"

echo "Volume check and creation process completed."

cd $STDP/03_ToyProjects/book-garden

echo "docker compose start"

docker-compose build --no-cache
docker-compose down
docker-compose up -d --build
echo "docker compose done"

echo "docker check container name : $(docker ps | grep ${DB_CONTAINER_NAME})"

docker logs -f ${APP_CONTAINER_NAME}
