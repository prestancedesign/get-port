# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

## [Unreleased]

## [0.2.0] - 2026-03-06

### Added
- `:fallback true` option to return a random available port when none of the preferred ports are available, instead of `nil`

### Changed
- `get-port` now returns `nil` when preferred port(s) are unavailable, instead of silently falling back to a random port
- `make-range` now validates that ports are within 1024-65535 and `from` <= `to`

### Fixed
- `get-port` threw `Don't know how to create ISeq from: java.lang.Long` when a single preferred port was unavailable

### Dependencies
- Upgraded Clojure from `1.12.0` to `1.12.4`

## [0.1.1] - 2021-02-25

### Added
- Initial release

[Unreleased]: https://github.com/prestancedesign/get-port/compare/v0.2.0...HEAD
[0.2.0]: https://github.com/prestancedesign/get-port/compare/v0.1.1...v0.2.0
[0.1.1]: https://github.com/prestancedesign/get-port/releases/tag/v0.1.1
