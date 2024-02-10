cd /deploy

sudo docker stop finance_api || true
sudo docker rm finance_api || true
sudo docker image rm finance_api || true
sudo docker load -i finance_api.tar
sudo docker run -d -p 3000:8080 -v /home/ec2-user/db:/home/db --name finance_api finance_api