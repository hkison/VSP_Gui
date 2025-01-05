FROM openjdk:17-slim
RUN apt-get update && apt-get install -y \
    x11-apps \
    libxext6 libxrender1 libxtst6 \
    && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY . /app
RUN javac PhilosophersGUI.java
CMD ["java", "PhilosophersGUI"]