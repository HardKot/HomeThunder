/** @type {import('next').NextConfig} */
import i18n from "./next-i18next.config.js";

const nextConfig = {
  reactStrictMode: true,
  i18n: i18n.i18n,
  typescript: {
    ignoreBuildErrors: false,
  },
};

export default nextConfig;
