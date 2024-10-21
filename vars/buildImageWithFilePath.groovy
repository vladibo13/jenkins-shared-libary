import com.example.Docker

def call(String dockerfilePath, String contextDir, String imageName) {
    return new Docker(this).buildDockerImageWithFilePath(dockerfilePath, contextDir, imageName)
}