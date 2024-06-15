import "reflect-metadata";
import { injectable, inject } from "inversify";
import { TYPES } from "@/ioc";
import { Client as MinioClient } from "minio";
import { IFileUtils, IMinioConfig, IFileStorage } from "../interface";


@injectable()
export class FileStorage implements IFileStorage {
  private readonly minioClient: MinioClient;

  constructor(
    @inject(TYPES.MinioConfig) private readonly MinioConfig: IMinioConfig,
    @inject(TYPES.FileUtils) private readonly fileUtils: IFileUtils,
  ) {
    const { port, endpoint: endPoint, accessKey, secretKey } = MinioConfig;
    this.minioClient = new MinioClient({
     port,
     endPoint,
     accessKey,
     secretKey,
     useSSL: false
    }) 
  }

  async uploadInTemp(file: string, fileData: Buffer) {
    const tempBucket = "temp-bucket";
    const newFile = this.fileUtils.updateName(file);

    if (!await this.minioClient.bucketExists(tempBucket)) 
      await this.minioClient.makeBucket(tempBucket)
     
    await this.minioClient.putObject(tempBucket, newFile, fileData) 

    return newFile;
  }
}
