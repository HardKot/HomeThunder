import { authenticationMiddleware } from "@/app/middleware/AuthenticationMiddleware";
import { createCsrfMiddleware } from '@edge-csrf/nextjs';
import {
  NextMiddleware,
  NextResponse
} from "next/server";

const csrfMiddleware = createCsrfMiddleware({
  cookie: {
    secure: process.env.NODE_ENV === 'production',
  },
});



function stackMiddlewares(functions: MiddlewareFactory[] = [], index = 0): NextMiddleware {
  const current = functions[index];
  if (current) {
    const next = stackMiddlewares(functions, index + 1);
    return current(next);
  }
  return () => NextResponse.next();
}


export const middleware = csrfMiddleware //stackMiddlewares([csrfMiddleware, authenticationMiddleware]);
