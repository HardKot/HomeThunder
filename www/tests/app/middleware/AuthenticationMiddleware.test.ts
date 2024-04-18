/**
 * @jest-environment node
 */

import {authenticationMiddleware} from "@/app/middleware/AuthenticationMiddleware";
import {NextRequest, NextResponse} from "next/server";
import { cookies } from "next/headers"


jest.mock("next/headers", () => {
  let cookieMockValue: string | null = null;
  return ({
    cookies: jest.fn(() => ({
      get: jest.fn(() => ({value: cookieMockValue})),
      __setMockCookie(mockValue: string) {
        cookieMockValue = mockValue;
      },
      __dropMockCookie() {
        cookieMockValue = null
      }
    }))
  })
})


describe("authenticationMiddleware", () => {
  const url = new URL("http://localhost")

  const redirectSpy = jest.spyOn(NextResponse, "redirect")
  const nextSpy = jest.spyOn(NextResponse, "next")


  afterEach(() => {
    redirectSpy.mockReset();
    nextSpy.mockReset()
    cookies().__dropMockCookie()
  });

  it("auth and request home page", () => {
    cookies().__setMockCookie("eyJhbGciOiJIUzM4NCJ9.eyJqdGkiOiIzMWY2YWU5Zi04N2ZiLTQ3YmMtYjkwYy1lNDVkMDNkZGY0MTgiLCJzdWIiOiJtaW5lci5oYXJkMDBAZ21haWwuY29tIiwicnVsZXMiOlsidXNlclJvbGVBZGRlZCIsInVzZXJSb2xlUmVtb3ZlZCIsInVzZXJSdWxlU2V0Il0sInVpZCI6Ijk3NWJiMWY0LWJjZGUtNDkyZi05YTk5LWExYzExOThkNTdiNiIsImlhdCI6MTcxMzM4NjA5OCwiZXhwIjoxNzEzMzg3ODk4fQ.ipQBeFwVS4cpB49L51LFoqsWtBc1kdBp2N7jb817GH7iDKaAmKPdUZe3CDhj_FTF")
    const req = new NextRequest(url, {});

    authenticationMiddleware(req)

    expect(nextSpy).toHaveBeenCalledTimes(1)
  })

  it("no auth and request home page", () => {
    const req = new NextRequest(url, {});

    authenticationMiddleware(req)

    expect(redirectSpy).toHaveBeenCalledTimes(1)
    expect(redirectSpy).toHaveBeenCalledWith('/login');
  })


  it("auth and request home, registration page", () => {
    cookies().__setMockCookie("eyJhbGciOiJIUzM4NCJ9.eyJqdGkiOiIzMWY2YWU5Zi04N2ZiLTQ3YmMtYjkwYy1lNDVkMDNkZGY0MTgiLCJzdWIiOiJtaW5lci5oYXJkMDBAZ21haWwuY29tIiwicnVsZXMiOlsidXNlclJvbGVBZGRlZCIsInVzZXJSb2xlUmVtb3ZlZCIsInVzZXJSdWxlU2V0Il0sInVpZCI6Ijk3NWJiMWY0LWJjZGUtNDkyZi05YTk5LWExYzExOThkNTdiNiIsImlhdCI6MTcxMzM4NjA5OCwiZXhwIjoxNzEzMzg3ODk4fQ.ipQBeFwVS4cpB49L51LFoqsWtBc1kdBp2N7jb817GH7iDKaAmKPdUZe3CDhj_FTF")
    const reqLogin = new NextRequest(new URL("/login", url), {});

    authenticationMiddleware(reqLogin)

    expect(redirectSpy).toHaveBeenCalledTimes(1)
    expect(redirectSpy).toHaveBeenCalledWith('/');
  })

  it("auth and request registration page", () => {
    cookies().__setMockCookie("eyJhbGciOiJIUzM4NCJ9.eyJqdGkiOiIzMWY2YWU5Zi04N2ZiLTQ3YmMtYjkwYy1lNDVkMDNkZGY0MTgiLCJzdWIiOiJtaW5lci5oYXJkMDBAZ21haWwuY29tIiwicnVsZXMiOlsidXNlclJvbGVBZGRlZCIsInVzZXJSb2xlUmVtb3ZlZCIsInVzZXJSdWxlU2V0Il0sInVpZCI6Ijk3NWJiMWY0LWJjZGUtNDkyZi05YTk5LWExYzExOThkNTdiNiIsImlhdCI6MTcxMzM4NjA5OCwiZXhwIjoxNzEzMzg3ODk4fQ.ipQBeFwVS4cpB49L51LFoqsWtBc1kdBp2N7jb817GH7iDKaAmKPdUZe3CDhj_FTF")
    const reqRegistration = new NextRequest(new URL("/registration", url), {});

    authenticationMiddleware(reqRegistration)

    expect(redirectSpy).toHaveBeenCalledTimes(1)
    expect(redirectSpy).toHaveBeenCalledWith('/');
  })
})
