/**
 * Metro configuration for React Native
 * https://github.com/facebook/react-native
 *
 * @format
 */
module.exports = {
  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: false
      }
    })
  },
  resolver: {
    blockList: [
      /\/Users\/tatumalenko\/dev\/MailrKt\/shared\/build\/tmp\/expandedArchives\/*/,
      /\/Users\/tatumalenko\/dev\/MailrKt\/shared\/build\/tmp\/publicPackageJson\/package.json/,
      /\/Users\/tatumalenko\/dev\/MailrKt\/shared\/build\/js\/packages\/shared\/package.json/,
      /node_modules\/.*\/node_modules\/react-native\/.*/
    ]
  }
};
