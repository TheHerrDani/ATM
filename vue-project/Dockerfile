FROM node:17.9.0-alpine
WORKDIR /opt/app
COPY ./package.json ./
RUN npm install --legacy-peer-dep
COPY . .
CMD ["npm", "run", "serve"]