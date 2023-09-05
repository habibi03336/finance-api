cd /api

sudo docker stop api || true
sudo docker rm api || true
sudo docker image rm api || true
sudo docker load -i api.tar
sudo docker run -d -p 80:8080 --name api api