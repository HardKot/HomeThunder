import { map } from "lodash";

const TYPES = {
  ApiRest: Symbol.for("ApiRest"),
  TokenManager: Symbol.for("TokenManager"),
  ApiRPC: Symbol.for("ApiRPC"),
  CSRFToken: Symbol.for("CSRFToken"),
  MinioConfig: Symbol.for("MinioConfig"),
  FileUtils: Symbol.for("FileUtils"),
  FileStorage: Symbol.for("FileStorage"),
};

export { TYPES };
