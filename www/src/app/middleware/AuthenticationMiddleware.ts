import {NextRequest, NextResponse} from "next/server";
import {AuthManager} from "@/shared/api/AuthManager";
import {RuleRouting} from "@/app/RuleRouting";

export const authenticationMiddleware = (request: NextRequest) => {
  const authManager = AuthManager.build();
  const token = authManager.getToken()
  if (!token && (
    !request.nextUrl.pathname.startsWith("/registration") ||
    !request.nextUrl.pathname.startsWith("/login") ||
    !request.nextUrl.pathname.startsWith("/login")
  )) return NextResponse.redirect("/login");


  if (request.nextUrl.pathname.startsWith("/registration") ||
    request.nextUrl.pathname.startsWith("/login") ||
    request.nextUrl.pathname.startsWith("/login")
  ) return NextResponse.redirect("/")

  const rules = RuleRouting(request.nextUrl.pathname);

  if (!rules.length) return NextResponse.next();

  for (const rule of rules) {
    if (authManager.hasAccess(rule)) return NextResponse.next();
  }

  return NextResponse.redirect(request.headers.get("referer") ?? "/")
}
