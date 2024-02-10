cd /deploy

sudo docker stop finance_api || true
sudo docker rm finance_api || true
sudo docker image rm finance_api || true
sudo docker load -i finance_api.tar
sudo docker run -d --network api_net -v /home/ec2-user/db:/home/db --name finance_api finance_api