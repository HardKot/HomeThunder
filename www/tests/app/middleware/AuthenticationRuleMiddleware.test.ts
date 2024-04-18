/**
 * @jest-environment node
 */

import {authenticationRuleMiddleware} from "@/app/middleware/AuthenticationRuleMiddleware";
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
    // @ts-ignore
    cookies().__dropMockCookie()
  });

  it("has access in private page", () => {
    // @ts-ignore
    cookies().__setMockCookie("eyJhbGciOiJIUzM4NCJ9.eyJqdGkiOiIzMWY2YWU5Zi04N2ZiLTQ3YmMtYjkwYy1lNDVkMDNkZGY0MTgiLCJzdWIiOiJtaW5lci5oYXJkMDBAZ21haWwuY29tIiwicnVsZXMiOlsicm9sZUNyZWF0ZWQiXSwidWlkIjoiOTc1YmIxZjQtYmNkZS00OTJmLTlhOTktYTFjMTE5OGQ1N2I2IiwiaWF0IjoxNzEzMzg2MDk4LCJleHAiOjE3MTMzODc4OTh9.9XcTnN0eO539stl3E5haX_pUJ8mK_MN2q7Ay7ufnAr_VSaK53u6ncPKkhoWnnhED")
    const req = new NextRequest(new URL('/role/1', url), {});
    req.headers.set("referer", "/")

    authenticationRuleMiddleware(req)

    expect(nextSpy).toHaveBeenCalledTimes(1)
  })

  it("no has access in private page", () => {
    // @ts-ignore
    cookies().__setMockCookie("eyJhbGciOiJIUzM4NCJ9.eyJqdGkiOiIzMWY2YWU5Zi04N2ZiLTQ3YmMtYjkwYy1lNDVkMDNkZGY0MTgiLCJzdWIiOiJtaW5lci5oYXJkMDBAZ21haWwuY29tIiwicnVsZXMiOltdLCJ1aWQiOiI5NzViYjFmNC1iY2RlLTQ5MmYtOWE5OS1hMWMxMTk4ZDU3YjYiLCJpYXQiOjE3MTMzODYwOTgsImV4cCI6MTcxMzM4Nzg5OH0.pZykwujLf24ZgZ1limaNhixUCUvUlu1NpPPILvSyHFw_5smMhj2MmRiIKt38fE_c")
    const req = new NextRequest(new URL('/role/1', url), {});
    req.headers.set("referer", "/")

    authenticationRuleMiddleware(req)

    expect(redirectSpy).toHaveBeenCalledTimes(1)
    expect(redirectSpy).toHaveBeenCalledWith(req.headers.get("referer"));
  })


  it("request public page", () => {
    // @ts-ignore
    cookies().__setMockCookie("eyJhbGciOiJIUzM4NCJ9.eyJqdGkiOiIzMWY2YWU5Zi04N2ZiLTQ3YmMtYjkwYy1lNDVkMDNkZGY0MTgiLCJzdWIiOiJtaW5lci5oYXJkMDBAZ21haWwuY29tIiwicnVsZXMiOltdLCJ1aWQiOiI5NzViYjFmNC1iY2RlLTQ5MmYtOWE5OS1hMWMxMTk4ZDU3YjYiLCJpYXQiOjE3MTMzODYwOTgsImV4cCI6MTcxMzM4Nzg5OH0.pZykwujLf24ZgZ1limaNhixUCUvUlu1NpPPILvSyHFw_5smMhj2MmRiIKt38fE_c")
    const reqLogin = new NextRequest(new URL("/", url), {});
    reqLogin.headers.set("referer", "/")

    authenticationRuleMiddleware(reqLogin)

    expect(nextSpy).toHaveBeenCalledTimes(1)
  })

  it("request private page, without, referer", () => {
    // @ts-ignore
    cookies().__setMockCookie("eyJhbGciOiJIUzM4NCJ9.eyJqdGkiOiIzMWY2YWU5Zi04N2ZiLTQ3YmMtYjkwYy1lNDVkMDNkZGY0MTgiLCJzdWIiOiJtaW5lci5oYXJkMDBAZ21haWwuY29tIiwicnVsZXMiOltdLCJ1aWQiOiI5NzViYjFmNC1iY2RlLTQ5MmYtOWE5OS1hMWMxMTk4ZDU3YjYiLCJpYXQiOjE3MTMzODYwOTgsImV4cCI6MTcxMzM4Nzg5OH0.pZykwujLf24ZgZ1limaNhixUCUvUlu1NpPPILvSyHFw_5smMhj2MmRiIKt38fE_c")
    const reqLogin = new NextRequest(new URL("/role/1", url), {});
    reqLogin.headers.delete("referer")

    authenticationRuleMiddleware(reqLogin)

    expect(redirectSpy).toHaveBeenCalledTimes(1)
    expect(redirectSpy).toHaveBeenCalledWith("/");
  })
})
