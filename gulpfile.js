var gulp = require('gulp'),
    _ = require('lodash'),
//    run = require('gulp-run'),
    runSequence = require('run-sequence'),
    clean = require('gulp-clean'),
    bower = require('gulp-bower'),
    gutil = require('gulp-util'),
    flatten = require('gulp-flatten'),
    rename = require('gulp-rename'),
    uglify = require('gulp-uglify'),
    jshint = require('gulp-jshint'),
    jsxhint = require('gulp-jsxhint'),
    concat = require('gulp-concat'),
    order = require('gulp-order'),
    livereload = require('gulp-livereload'),
    del = require('del'),
    browserify = require('browserify'),
    source = require('vinyl-source-stream'),
    buffer = require('vinyl-buffer'),
    reactify = require('reactify'),
    sass = require('gulp-sass'),
    package = require('./package.json');

var jsx_modules_path = package.dest.jsx;
var public_path = package.dest.public;

var static_dir = './src/main/resources/static/';

// add custom browserify options here
var customOpts = {
    entries: [package.paths.app],
    debug: true,
    standalone: 'Coinche'
};

gulp.task('bower:install', function () {
    //run('bower install').exec();
    return bower();
});


gulp.task('jsx', function () {
    return browserify(customOpts)
        .transform(reactify)
        .bundle()
        .on('log', gutil.log)
        .on('error', gutil.log.bind(gutil, 'JSX Error'))
        .pipe(source(package.dest.app))
        .pipe(gulp.dest(package.dest.dist))
        .pipe(livereload());
});

gulp.task('jsx:min', function () {

    return browserify(customOpts)
        .transform(reactify)
        .plugin('minifyify', {
            map: 'bundle.min.map',
            output:'src/main/resources/static/js/bundle.min.map'
        })
        .bundle()
        .pipe(source(package.dest.app))
        .pipe(rename({
            suffix: ".min"
        }))
        .pipe(gulp.dest(package.dest.dist))
        .pipe(livereload());
});

gulp.task('watch', ['jsx', 'sass'], function () {
    livereload.listen();
    return gulp.watch([
        package.paths.js,
        package.paths.templates,
        package.paths.scss
    ], [
        'jsx','sass'
    ]);
});

gulp.task('sass', function () {
    console.log("sass");
    return gulp.src(package.paths.scss)
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest(package.dest.css));
});


gulp.task('build-clean', function () {
    return gulp.src(package.dest.public).pipe(clean());
});

gulp.task('build', function (cb) {
    runSequence('build-clean', 'bower:install', 'jsx', 'sass', cb);
});

gulp.task('default', ['build']);
