import { NextRequest } from "next/server";
import { authenticationMiddleware } from "@/app/middleware/AuthenticationMiddleware";

export default function middleware(req: NextRequest) {
  return authenticationMiddleware(req);
}
