FROM openjdk:8-jdk-alpine

ARG SUID=1042
ARG SGID=1042

VOLUME /tmp

# Create a working directory
RUN mkdir /app
WORKDIR /app

# Copy necessary files
ADD target/snowstorm-*.jar snowstorm.jar

# Create the snowstorm user
RUN addgroup -g $SGID snowstorm && \
    adduser -D -u $SUID -G snowstorm snowstorm

# Change permissions.
RUN chown -R snowstorm:snowstorm /app

# Run as the snowstorm user.
USER snowstorm

ENTRYPOINT ["java","-jar","snowstorm.jar"]
