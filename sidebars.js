// sidebars.js

/** @type {import('@docusaurus/plugin-content-docs').SidebarsConfig} */
const sidebars = {
  mallocSidebar: [
    {
      type: 'doc',
      id: 'intro',
      label: 'Introduction',
    },
    {
      type: 'doc',
      id: 'getting-started',
    },
    {
      type: 'doc',
      id: 'installation',
    },
    {
      type: 'category',
      label: 'API Reference',
      items: [
        'api-reference/authentication',
        'api-reference/encryption',
        // Add more files as needed
      ],
    },
    {
      type: 'category',
      label: 'Examples',
      items: [
        'examples/android-kotlin',
        'examples/android-java',
      ],
    },
    {
      type: 'doc',
      id: 'troubleshooting',
    },
  ],
};

export default sidebars;
