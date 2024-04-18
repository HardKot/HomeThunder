import {NextRequest, NextResponse} from "next/server";
import {AuthManager} from "@/shared/api/AuthManager";
import {RuleRouting} from "@/app/RuleRouting";

export const authenticationRuleMiddleware = (request: NextRequest) => {
  const rules = RuleRouting(request.nextUrl.pathname);
  const authManager = AuthManager.build();


  if (!rules.length) return NextResponse.next();

  for (const rule of rules) {
    if (authManager.hasAccess(rule)) return NextResponse.next();
  }

  const referer = request.headers.get("referer") ?? "/"

  return NextResponse.redirect(referer)
}
