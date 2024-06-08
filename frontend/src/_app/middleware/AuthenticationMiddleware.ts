import { NextRequest, NextResponse } from "next/server";
import { AuthManager } from "@/shared/api/AuthManager";
import { RuleRouting } from "@/app/RuleRouting";

const publicRoutes = ["/login", "/registration"];

export const authenticationMiddleware = (request: NextRequest) => {
  return NextResponse.next();
};
