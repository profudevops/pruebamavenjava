const { execSync } = require('child_process');

let env = '';

if (process.argv[2]) {
  env = `--mode ${process.argv[2]}`;
}

execSync('npm run clean:dist', { stdio: [0, 1, 2] });
execSync(`npm run prebuild -- ${env}`, { stdio: [0, 1, 2] });
execSync('npm run copy', { stdio: [0, 1, 2] });
