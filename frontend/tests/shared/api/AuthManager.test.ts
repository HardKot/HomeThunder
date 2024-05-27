import {AuthManager} from "@/shared/api/AuthManager";
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

describe("AuthManager", () => {
  const authManager = AuthManager.build();

  afterEach(() => {
    cookies().__dropMockCookie()
  })

  it("hasAccess: has accuse", () => {
    cookies().__setMockCookie("eyJhbGciOiJIUzM4NCJ9.eyJqdGkiOiIzMWY2YWU5Zi04N2ZiLTQ3YmMtYjkwYy1lNDVkMDNkZGY0MTgiLCJzdWIiOiJtaW5lci5oYXJkMDBAZ21haWwuY29tIiwicnVsZXMiOlsidXNlclJvbGVBZGRlZCIsInVzZXJSb2xlUmVtb3ZlZCIsInVzZXJSdWxlU2V0Il0sInVpZCI6Ijk3NWJiMWY0LWJjZGUtNDkyZi05YTk5LWExYzExOThkNTdiNiIsImlhdCI6MTcxMzM4NjA5OCwiZXhwIjoxNzEzMzg3ODk4fQ.ipQBeFwVS4cpB49L51LFoqsWtBc1kdBp2N7jb817GH7iDKaAmKPdUZe3CDhj_FTF");

    expect(authManager.hasAccess("userRoleAdded")).toBeTruthy()
  })

  it("hasAccess: no has accuse", () => {
    cookies().__setMockCookie("eyJhbGciOiJIUzM4NCJ9.eyJqdGkiOiIzMWY2YWU5Zi04N2ZiLTQ3YmMtYjkwYy1lNDVkMDNkZGY0MTgiLCJzdWIiOiJtaW5lci5oYXJkMDBAZ21haWwuY29tIiwicnVsZXMiOlsidXNlclJvbGVSZW1vdmVkIiwidXNlclJ1bGVTZXQiXSwidWlkIjoiOTc1YmIxZjQtYmNkZS00OTJmLTlhOTktYTFjMTE5OGQ1N2I2IiwiaWF0IjoxNzEzMzg2MDk4LCJleHAiOjE3MTMzODc4OTh9.LspAU91dNW8VVg428Gd7pppG3W7PsIHIu84nvoRcBCzccFvKQ7VT8v7-AoFSMd01")

    expect(authManager.hasAccess("userRoleAdded")).toBeFalsy()
  })
})
