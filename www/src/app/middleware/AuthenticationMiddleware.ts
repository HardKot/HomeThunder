import {NextRequest, NextResponse} from "next/server";
import {AuthManager} from "@/shared/api/AuthManager";

export const authenticationMiddleware = (request: NextRequest) => {
  if (!AuthManager.build().getToken()) return NextResponse.redirect("/login");
  if (request.nextUrl.pathname.startsWith("/login") || request.nextUrl.pathname.startsWith("/registration")) return NextResponse.redirect("/")
  return NextResponse.next();
}
