cd /api

sudo docker stop api || true
sudo docker rm api || true
sudo docker image rm api || true
sudo docker load -i api.tar
sudo docker run -d -p 3000:8080 -v /home/ec2-user/db:/home/db --name api api