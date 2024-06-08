import { PropsWithChildren } from "react";
import { Metadata } from "next";
import { CombineProvider } from "@/app/providers/CombineProvider";

export default function RootLayout({ children }: PropsWithChildren) {
  return (
    <html lang="ru">
      <body>
        <CombineProvider>{children}</CombineProvider>
      </body>
    </html>
  );
}

export const metadata: Metadata = {
  title: "Home",
  description: "Welcome to Next.js",
};
