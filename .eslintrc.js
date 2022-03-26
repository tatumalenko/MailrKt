module.exports = {
  root: true,
  extends: '@react-native-community',
  overrides: [
    {
      files: ['*.ts', '*.tsx', '*.js'],
      rules: {
        indent: ['error', 2],
        'no-shadow': 'off',
        'no-undef': 'off',
        quotes: ['error', 'single'],
        'comma-dangle': ['error', 'never']
      }
    }
  ]
};
