import {NextRequest, NextResponse} from "next/server";
import {AuthManager} from "@/shared/api/AuthManager";
import {RuleRouting} from "@/app/RuleRouting";

const publicRoutes = ['/login', '/registration']


export const authenticationMiddleware = (request: NextRequest) => {
  const authManager = AuthManager.build();
  const token = authManager.getToken()


  if (!token && !publicRoutes.find(publicRoute => request.nextUrl.pathname.startsWith(publicRoute)))
    return NextResponse.redirect(new URL("/login", request.url));


  if (publicRoutes.find(publicRoute => request.nextUrl.pathname.startsWith(publicRoute)))
    return NextResponse.redirect( new URL("/", request.url))

  const rules = RuleRouting(request.nextUrl.pathname);

  if (!rules.length) return NextResponse.next();

  for (const rule of rules) {
    if (authManager.hasAccess(rule)) return NextResponse.next();
  }

  return NextResponse.redirect(new URL(request.headers.get("referer") ?? "/", request.url))
}
